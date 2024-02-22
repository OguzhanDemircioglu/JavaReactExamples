import React from 'react';
import {Persons} from "../helper/Persons";
import ExportExcel from "../helper/ExportExcel";
import ImportExcel from "../helper/ImportExcel";

function ExcelCruds() {

    return (
        <div className="example">
        <ExportExcel props ={Persons}/>
            <ImportExcel/>
        </div>
    );
}

export default ExcelCruds;