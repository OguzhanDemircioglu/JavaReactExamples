import Navbar from "react-bootstrap/Navbar";
import React from "react";
import { Container, Nav, NavLink } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faVideoSlash } from "@fortawesome/free-solid-svg-icons/faVideoSlash";
import "../App.css";

export default function Header() {
    return (
        <Navbar bg="dark" variant="dark" expand="lg">
            <Container fluid>
                <Navbar.Brand href="/" style={{ color: "gold" }}>
                    <FontAwesomeIcon icon={faVideoSlash} />
                    Gold
                </Navbar.Brand>
                <Nav
                    className="me-auto my-2 my-lg-0"
                >
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
    );
};
