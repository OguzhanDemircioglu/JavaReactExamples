import React, {useReducer, useState} from 'react';
import Todo from "../helper/Todo";

export default function UseReducerExample() {

    const [state, dispatch] = useReducer(countReducer, {count: 0});
    const [todos, dispatchTodo] = useReducer(todoReducer, []);

    const [name, setName] = useState('');

    function countReducer(state, action) {
        switch (action.type) {
            case "increment":
                return {count: state.count + 1}
            case "decrement":
                return {count: state.count - 1}
            default:
                return state
        }
    }

    function decreaseCount() {
        dispatch({type: "decrement"});
    }

    function increaseCount() {
        dispatch({type: "increment"});
    }

//////////////////////////////

    function todoReducer(todos, action) {
        switch (action.type) {
            case "add":
                return [...todos, newTodo(action.payload.name)];
            case "toggle":
                return todos.map(todo => {
                    if (todo.id === action.payload.id) {
                        return {...todo, complete: !todo.complete}
                    } else {
                        return todo;
                    }
                })
            case "delete":
                return todos.filter(todo => todo.id !== action.payload.id);
            default:
                return todos;
        }
    }

    function handleSubmit(e) {
        e.preventDefault();
        dispatchTodo({type: "add", payload: {name: name}})
        setName('');
    }

    function newTodo(name) {
        return {id: Date.now(), name: name, complete: false}
    }

    return (<div className="example">
            <button onClick={decreaseCount}
                    style={{width: 50, marginTop: 50, marginLeft: 50, border: "none", outline: "none"}}>-
            </button>
            <span>  {state.count}  </span>
            <button onClick={increaseCount} style={{width: 50, border: "none", outline: "none"}}>+</button>
            <br/>
            <form onSubmit={handleSubmit}>
                <input type="text" value={name} onChange={e => setName(e.target.value)}/>
            </form>
            {todos.map(todo => {
                return <div>
                    <Todo key={todo.id} todo={todo} dispatch={dispatchTodo}/>
                </div>
            })}
        </div>);
}
