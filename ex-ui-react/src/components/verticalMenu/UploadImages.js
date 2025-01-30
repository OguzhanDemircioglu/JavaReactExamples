import React, {useState} from 'react';
import axios from "axios";
import {CLOUD_NAME} from "../../config/Config";
import {ThreeDots} from "react-loader-spinner";

function UploadImages() {

    const [images, setImages] = useState(null);
    const [loading, setLoading] = useState(false);
    const [files, setFiles] = useState([]);
    const [error, setError] = useState(null);

    function uploadImages(e) {
        e.preventDefault();

        if (!files) return;

        let maxSizeInBytes = 3 * 1024 * 1024; // 3 MB
        let oversizedFiles = Array.from(files).filter(file => file.size > maxSizeInBytes);

        if (oversizedFiles.length > 0) {
            alert("Dosya boyutu çok büyük. Lütfen 3 MB'den küçük dosyalar yükleyin.");
            setLoading(false);
            return;
        }

        let validFiles = Array.from(files).filter(file => {
            if (!file.name.toLowerCase().endsWith('.gif') &&
                !file.name.toLowerCase().endsWith('.jpeg') &&
                !file.name.toLowerCase().endsWith('.jpg') &&
                !file.name.toLowerCase().endsWith('.png')) {
                return true;
            }
        })

        if (validFiles.length > 0) {
            alert("Not an Image file");
            return;
        }

        setLoading(true);
        let arr = [];
        setError(null);
        const uploadPromises = Array.from(files).map(file => {
            const formData = new FormData();
            formData.append('upload_preset', "imageCloud");
            formData.append('file', file);

            return axios.post(`https://api.cloudinary.com/v1_1/${CLOUD_NAME}/image/upload`, formData)
                .then(res => {
                    arr.push(res.data.url);
                })
                .catch(err => {
                    setError(err.message);
                });
        });

        Promise.all(uploadPromises)
            .then(() => {
                setImages(arr);
                setLoading(false);
            });
    }

    return (
        <div className="example">
            <form onSubmit={uploadImages}>
                    <input
                        type="file"
                        accept="image/gif, image/jpeg, image/png, image/jpg"
                        multiple={true}
                        onChange={(e) => setFiles(e.target.files)}
                    />
                    <button type="submit">Upload</button>
            </form>
            {loading && <ThreeDots
                height="80"
                width="80"
                radius="9"
                color="#4fa94d"
                ariaLabel="three-dots-loading"
                wrapperStyle={{}}
                wrapperClassName=""
                visible={true}
            />}

            {error && <div>Error: {error}</div>}

            {images && !error && (
                <div>
                    <h2>Cloudinary Images</h2>
                    {images.map((i, index) => (
                        <img key={index} style={{width: "200px", height: "200px", marginLeft: "10px"}} src={i}
                             alt="Cloudinary Image"/>
                    ))}
                </div>
            )}
        </div>
    );
}

export default UploadImages;