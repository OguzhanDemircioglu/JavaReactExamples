import React, {useCallback, useState} from 'react';

export default function UseCallBackExample() {
    const [number, setNumber] = useState(0);

    const getList = useCallback((e) => {
        return [number+e+1, number+e+2, number+e+3];
    }, [number]);
    return (
        <div>
            <input value={number} type="number" onChange={e => setNumber(Number(e.target.value))} />
            {
                getList(number).map((e, index) => (
                    <div key={index}>{e}</div>
                ))
            }
        </div>
    );
}