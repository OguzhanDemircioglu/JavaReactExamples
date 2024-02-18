import React, {useCallback, useState} from 'react';
import "../App.css";

export default function UseCallBackExample() {
    const [number, setNumber] = useState(0);

    const getList = useCallback((e) => {
        return [number+e+1, number+e+2, number+e+3];
    }, [number]);
    return (
        <div className="example">
            <input value={number} type="number" onChange={e => setNumber(Number(e.target.value))} />
            {
                getList(number).map((e, index) => (
                    <div key={index}>{e}</div>
                ))
            }
        </div>
    );
}