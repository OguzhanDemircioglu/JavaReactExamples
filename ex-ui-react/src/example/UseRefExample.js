import React, {useEffect, useRef, useState} from 'react';

export default function UseRefExample() {
    const [name, setName] = useState('');
    const renderCount = useRef(1);
    const previousName = useRef('');

    useEffect(() => {
        renderCount.current++;
        console.log("say");
        previousName.current = name;
    },[name]);

    return (
        <div className="example">
            <input value={name} onChange={e => setName(e.target.value)}/>
            <p>My name is {name}</p>
            <p>My previousName is {previousName.current}</p>
            <p>I rendered {renderCount.current} times</p>
        </div>
    );
}
