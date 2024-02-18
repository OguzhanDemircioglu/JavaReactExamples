import React, {useState} from 'react';

let previousColor = "";

export default function UseStateExample() {
    const [count, setCount] = useState(0);
    const [color, setColor] = useState('red');
    function decreaseCount(){
        setCount(count=> count-1);
        previousColor = color;
        setColor("orange");
    }

    function increaseCount() {
        setCount(count=> count+1);
        previousColor = color;
        setColor("black");
    }

    return (
        <div className="example">
            <button onClick={decreaseCount} style={{width:50, marginTop:50, marginLeft:50,border:"none",outline:"none",backgroundColor:previousColor}}>-</button>
            <span>  {count} -  {color}  </span>
            <button onClick={increaseCount} style={{width:50,border:"none",outline:"none",backgroundColor:color}}>+</button>
        </div>
    );
}
