import React, {useEffect, useRef} from 'react';
import {CLOUD_NAME} from "../../config/Config";

const ImageWidget = () => {

   const cloudinaryRef = useRef();
    const widgetRef = useRef();

    useEffect(() => {
        cloudinaryRef.current = window.cloudinary;
        widgetRef.current = cloudinaryRef.current.createUploadWidget({
            cloudName: CLOUD_NAME,
            uploadPreset: 'imageCloud'
        }, function (error,result) {
            console.log(result)
        })
    }, []);

    return (
        <div className="example">
            <button onClick={()=>widgetRef.current.open()}>Upload</button>

        </div>
    );
};

export default ImageWidget;