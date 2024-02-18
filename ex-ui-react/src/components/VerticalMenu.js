import React from 'react';
import "../App.css";
import {Nav} from "react-bootstrap";
import {NavLink} from "react-router-dom";
import {Container} from "@mui/material";

const VerticalMenu = () => {
    return (
        <div className="side-nav">
            <Container>
                <Nav>
                    <NavLink className="nav-link" style={{color: "darkslategray", fontSize: "20px"}} to={"/dataTableEx"}>
                        DataTableEx
                    </NavLink>
                </Nav>

            </Container>
        </div>
    );
}

export default VerticalMenu;