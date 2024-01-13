import React, {useEffect, useMemo, useState} from 'react';

function UseMemoExample() {
    const [count, setCount] = useState(0);
    const [dark, setDark] = useState(false);

    function slowFunction(num){
        for (let i = 0; i < 100000000; i++) {}
        return num*2
    }

    //useMemo Heap deki değeri tutar

    const countMemo = useMemo(() => {
        return slowFunction(count);
    }, [count]);//return değeri int  olduğu için burada useMemo çalışmaz

/*    const themeStyle = {
        backgroundColor : dark ? 'black' : 'red',
        color : dark ? 'white' : 'yellow'
    }*/

    const themeStyle = useMemo(() => {
        return{
        backgroundColor : dark ? 'black' : 'red',
        color : dark ? 'white' : 'yellow'
        }
    },[dark]);//return değeri object olduğu için sayfada olan herhangi bir değişim useMemo çalışır
    //Yukarıdaki themeStyle objesi yorumdan kaldırılıp denenebilir

    useEffect(() => {
        console.log("theme Changed");
    }, [themeStyle]);

    return (
        <>
        <input type="number" value={count} onChange={e => setCount(Number(e.target.value))}/>
            <button style={themeStyle} onClick={() => setDark(darkVal => !darkVal)}> Change Theme</button>
        <div>{count} * 2 = {countMemo}</div>
        </>
    );
}

export default UseMemoExample;