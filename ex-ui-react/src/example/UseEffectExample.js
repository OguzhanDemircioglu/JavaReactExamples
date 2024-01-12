import React, {useState, useEffect} from 'react';

export default function UseEffectExample() {
    const [resourceType, setResourceType] = useState('posts');
    const [item, setItem] = useState([]);
    const [windowWidth, setWindowWidth] = useState(window.innerWidth);

    const handleResize = () => {
        setWindowWidth(window.innerWidth);
    }

    useEffect(() => {

        window.addEventListener('resize', handleResize);

        return () => {
            fetch(`https://jsonplaceholder.typicode.com/${resourceType}`)
                .then(response => response.json())
                .then(json => setItem(json));

            window.removeEventListener('resize', handleResize);

        };
    }, [resourceType]);

    return (
        <div>
            <div>
                <button onClick={() => setResourceType('posts')}>Posts</button>
                <button onClick={() => setResourceType('users')}>Users</button>
                <button onClick={() => setResourceType('comments')}>Comments</button>
            </div>
            <h1>{resourceType}</h1>
            <h1>innerWidth = {windowWidth}</h1>
            {
                item.map(it => {
                    return <>
                        <pre>{JSON.stringify(it)}</pre>
                    </>
                })
            }
        </div>
    )
}
