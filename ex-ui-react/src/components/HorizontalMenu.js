import Navbar from "react-bootstrap/Navbar";
import React from "react";
import {Container, Nav, NavLink} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faVideoSlash} from "@fortawesome/free-solid-svg-icons/faVideoSlash";
import "../App.css";

export default function HorizontalMenu() {
    return (
        <div className="horizontalMenu">
            <Navbar bg="dark" variant="dark">
                <Container fluid>
                    <Navbar.Brand href="/" style={{color: "gold",width: "165px",marginLeft:"30px"}}>
                        <FontAwesomeIcon icon={faVideoSlash}/>
                        Dear Vault
                    </Navbar.Brand>
                    <Nav style={{flex: "max-content" }}>
                        <NavLink className="nav-link" href="/useEffect">
                            UseEffect
                        </NavLink>
                        <NavLink className="nav-link" href="/useRef">
                            UseRef
                        </NavLink>
                        <NavLink className="nav-link" href="/useContext">
                            UseContext
                        </NavLink>
                        <NavLink className="nav-link" href="/useState">
                            UseState
                        </NavLink>
                        <NavLink className="nav-link" href="/useMemo">
                            UseMemo
                        </NavLink>
                        <NavLink className="nav-link" href="/useReducer">
                            UseReducer
                        </NavLink>
                        <NavLink className="nav-link" href="/useCallBack">
                            UseCallBack
                        </NavLink>
                    </Nav>
                </Container>
            </Navbar>
        </div>
    );
};
