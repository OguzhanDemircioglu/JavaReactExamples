import React, {useState} from 'react';
import DataTable, {Alignment, createTheme, Direction, Media} from 'react-data-table-component';
import {Button, Input} from "@mui/material";
import {Persons} from "../helper/Persons";
import {ArrowDownward} from "@mui/icons-material";
import * as XLSX from 'xlsx';

function DataTableEx() {

    const [items, setItems] = useState(Persons);

    const justArray = [
        ["takım","score","score","takım"],
        ["TS","1","0","GS"]
    ];

    const simpleColumns = [
        {
            name: 'Name',
            selector: row => row.name,
            sortable: true
        }, {
            name: 'Email',
            selector: row => row.email,
            sortable: true,
            hide: Media.SM
        }, {
            name: 'Age',
            selector: row => row.age,
            sortable: true
        }
    ];

    const columns = [
        {
            name: 'Name',
            selector: row => row.name,
            sortable: true,
            left: true
        },
        {
            name: 'Email',
            selector: row => row.email,
            sortable: true,
            center: true
        },
        {
            name: 'Ages',
            selector: row => row.age,
            sortable: true,
            right: true,
            conditionalCellStyles: [
                {
                    when: row => row.age < 10,
                    style: {
                        backgroundColor: 'rgba(63, 195, 128, 0.9)',
                        color: 'white',
                        '&:hover': {
                            cursor: 'pointer',
                        },
                    },
                },
                {
                    when: row => row.age >= 10 && row.age < 20,
                    style: {
                        backgroundColor: 'rgba(248, 148, 6, 0.9)',
                        color: 'white',
                        '&:hover': {
                            cursor: 'pointer',
                        },
                    },
                },
                {
                    when: row => row.age >= 20,
                    style: {
                        backgroundColor: 'rgba(242, 38, 19, 0.9)',
                        color: 'white',
                        '&:hover': {
                            cursor: 'not-allowed',
                        },
                    },
                }
            ],
        },
        {
            name: 'CRUD',
            cell: () => <Button>Download</Button>
        },
        {
            name: 'Person Link',
            button: true,
            cell: row => (
                <a href={row.name} target="_blank" rel="noopener noreferrer">
                    Show
                </a>
            ),
            style: {
                backgroundColor: 'orange',
                color: 'white',
                '&:hover': {
                    cursor: 'not-allowed',
                },
            }
        }
    ];

    const customStyles = {
        rows: {
            style: {
                minHeight: '40px', // override the row height
            },
        },
        headCells: {
            style: {
                paddingLeft: '8px', // override the cell padding for head cells
                paddingRight: '8px',
            },
        },
        cells: {
            style: {
                paddingLeft: '8px', // override the cell padding for data cells
                paddingRight: '8px',
            },
        },
    };

    createTheme('solarized', {
        text: {
            primary: '#268bd2',
            secondary: '#2aa198',
        },
        background: {
            default: '#002b36',
        },
        context: {
            background: '#cb4b16',
            text: '#FFFFFF',
        },
        divider: {
            default: '#073642',
        },
        action: {
            button: 'rgba(0,0,0,.54)',
            hover: 'rgba(0,0,0,.08)',
            disabled: 'rgba(0,0,0,.12)',
        },
    }, 'dark');

    function handleFilter(e) {

        const filteredData = Persons?.filter(row => {
            return row.name.toLowerCase().includes(e.target.value)
        });

        setItems(filteredData);
    }

    const ExpandedComponent = ({data}) => <pre>{JSON.stringify(data, null, 2)}</pre>;

    function exportExcel(){
        let wb = XLSX.utils.book_new(),ws = XLSX.utils.json_to_sheet(items);
        XLSX.utils.book_append_sheet(wb,ws,"MyShiit");
        XLSX.writeFile(wb,"MyExcel.xlsx");
    }

    function exportArrayExcel(){
        let wb = XLSX.utils.book_new(),
            ws = XLSX.utils.aoa_to_sheet(justArray);
        XLSX.utils.book_append_sheet(wb,ws,"MyArrayShiit");
        XLSX.writeFile(wb,"MyArrayExcel.xlsx");
    }

    return (
        <div className="example">
            <Input type="text" placeholder="SearchByName" onChange={handleFilter}/>
            <DataTable
                columns={simpleColumns}
                data={items}
                title="Persons"
                /*expandableRows*/
                /*expandableRowsComponent={ExpandedComponent}*/
                /*fixedHeader*/
                selectableRows
                pagination
                sortIcon={<ArrowDownward/>}
                customStyles={customStyles}
                /*theme="solarized"*/
                theme="dark"
                direction={Direction.AUTO}
                subHeaderAlign={Alignment.LEFT}
            />
            <div>
                    <Button style={{backgroundColor: "tomato", marginTop: "5px",marginRight:"10px"}}
                            onClick={exportExcel}>Export Excel</Button>

                    <Button style={{backgroundColor: "orange", marginTop: "5px"}}
                            onClick={exportArrayExcel}>Export Array Excel</Button>

            </div>
        </div>
    );
}

export default DataTableEx;