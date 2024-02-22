import React, {useRef, useState} from 'react';
import {Col, Row} from "react-bootstrap";
import * as XLSX from "xlsx";

const ImportExcel = () => {

    const [excelFile, setExcelFile] = useState(null);
    const importedExcelRef = useRef();

    const [fileName, setFileName] = useState(null);

    const importExcel = async (e) => {
        let file = e.target.files[0];

        if (!file) {
            alert('Not Selected');
            return;
        }

        if (!file.name.toLowerCase().endsWith('.xls') &&
            !file.name.toLowerCase().endsWith('.xlsx')) {
            alert("Not an Excel file");
            return;
        }

        setFileName(file.name);

        setExcelFile(file.name);

        let data = await file.arrayBuffer();
        let workBook = XLSX.read(data);
        //let workBook = XLSX.readFile(data,{sheetRows:5});//Sadece 5 satır oku

        let workSheet = workBook.Sheets[workBook.SheetNames[0]];
        let jsonData = XLSX.utils.sheet_to_json(workSheet);

        for (let i = 0; i <workBook.SheetNames.length ; i++) {
            console.log(
                XLSX.utils.sheet_to_json(
                    workBook.Sheets[workBook.SheetNames[i]]
                )
            );
        }

        setExcelFile(JSON.stringify(jsonData));
    }

    function removeExcel() {
        setFileName(null);
        setExcelFile(null);
        importedExcelRef.current.value = null;
    }

    return (
        <div className="example">
            {/*  <Row>
                <Col>
                    <Input
                        type="file"
                        ref={importedExcelRef}
                        onChange={e => importExcel(e)}
                        style={{color: "lightgray", marginTop: "5px", marginLeft: "10px"}}/>
                </Col>
            </Row>*/}
            <Row>
                <Col>
                    <div>
                        {fileName && <label><br/>Upload File is {fileName}</label>}
                        {!fileName && <label><br/>Please Upload a File</label>}
                    </div>
                    <div>
                        <input
                            accept="xls,xlsx"
                            multiple={false}
                            type="file"
                            ref={importedExcelRef}
                            onChange={e => importExcel(e)}
                        />
                        {fileName &&
                            <button onClick={removeExcel}>
                                Sil</button>
                        }
                    </div>
                </Col>
            </Row>
            {excelFile &&
                <p><br/>File: <br/><span>{excelFile}</span></p>
            }
        </div>
    );
};

export default ImportExcel;