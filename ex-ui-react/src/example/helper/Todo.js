import React from 'react';

export default function Todo({todo, dispatch}) {
    return (
        <div>
        <span style={{color: todo.complete ? 'red' : 'blue', backgroundColor: todo.complete ? 'yellow' : 'wheat'}}>
            {todo.name}
        </span>
            <button onClick={() => dispatch({type: "toggle", payload: {id: todo.id}})}>Toggle</button>
            <button onClick={() => dispatch({type: "delete", payload: {id: todo.id}})}>Delete</button>
        </div>
    );
}
