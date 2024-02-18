import React, {useContext} from 'react';
import {ThemeContext} from "../horizontalMenu/UseContextExample";

export default function FunctionContextComponent() {
    const darkTheme = useContext(ThemeContext);
    const themeStyles = {
            backgroundColor: darkTheme ? "#CCC" : "#333",
            color: darkTheme ? "#333" : "#CCC",
            padding: "2rem",
            margin: "2rem"
    }
    return (
        <div style={themeStyles}>
            Function Theme
        </div>
    );
}
