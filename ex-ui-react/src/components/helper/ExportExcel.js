import React from 'react';
import * as XLSX from "xlsx";
import {Button} from "@mui/material";

const ExportExcel = ({props}) => {

    const justArray = [
        ["takım", "score", "score", "takım"],
        ["TS", "1", "0", "GS"]
    ];

    function exportExcel() {
        let wb = XLSX.utils.book_new(), ws = XLSX.utils.json_to_sheet(props);
        XLSX.utils.book_append_sheet(wb, ws, "MyShiit");
        XLSX.writeFile(wb, "MyExcel.xlsx");
    }

    function exportArrayExcel() {
        let wb = XLSX.utils.book_new(),
            ws = XLSX.utils.aoa_to_sheet(justArray),
            ws1 = XLSX.utils.aoa_to_sheet(justArray);
        XLSX.utils.book_append_sheet(wb, ws, "MyArrayShiit");
        XLSX.utils.book_append_sheet(wb, ws1, "MyArrayShiit12");

        XLSX.writeFile(wb, "MyArrayExcel.xlsx");
    }

    return (
        <div className="example">
            <Button style={{backgroundColor: "tomato", marginTop: "5px"}}
                    onClick={exportExcel}>Export Excel</Button>

            <Button style={{backgroundColor: "orange", marginTop: "5px", marginLeft: "10px"}}
                    onClick={exportArrayExcel}>Export Array Excel</Button>
        </div>

    );
};

export default ExportExcel;