import './App.css';
import UseEffectExample from "./example/UseEffectExample";
import { Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Home from "./components/Home";
import UseRefExample from "./example/UseRefExample";
import UseContextExample from "./example/UseContextExample";
import UseStateExample from "./example/UseStateExample";
import UseMemoExample from "./example/UseMemoExample";
import UseReducerExample from "./example/UseReducerExample";

function App() {
  return (
    <div className="App">
        <Header />
        <Routes>
            <Route path="/"           element={<Home />}></Route>
            <Route path="/useEffect"  element={<UseEffectExample />}></Route>
            <Route path="/useRef"     element={<UseRefExample />}></Route>
            <Route path="/useContext" element={<UseContextExample />}></Route>
            <Route path="/useState"   element={<UseStateExample />}></Route>
            <Route path="/useMemo"    element={<UseMemoExample />}></Route>
            <Route path="/useReducer"    element={<UseReducerExample />}></Route>

        </Routes>
    </div>
  );
}

export default App;
