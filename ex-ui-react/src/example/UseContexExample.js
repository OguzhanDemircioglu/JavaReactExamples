import React, {useState} from 'react';
import FunctionContextComponent from "./helper/FunctionContextComponent";
import ClassContextComponent from "./helper/ClassContextComponent";

export const ThemeContext = React.createContext();

export default function UseContexExample() {
    const [darkTheme, setDarkTheme] = useState(true);

    function toggleTheme() {
        setDarkTheme(prevTheme => !prevTheme);
    }

    return (
        <>
            <ThemeContext.Provider value={darkTheme}>
                <button onClick={toggleTheme}>Toggle Theme</button>
                <FunctionContextComponent/>
                <ClassContextComponent/>
            </ThemeContext.Provider>
        </>
    );
}
