import './App.css';
import UseEffectExample from "./example/UseEffectExample";
import { Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Home from "./components/Home";
import UseRefExample from "./example/UseRefExample";
import UseContexExample from "./example/UseContexExample";

function App() {
  return (
    <div className="App">
        <Header />
        <Routes>
            <Route path="/"           element={<Home />}></Route>
            <Route path="/useEffect"  element={<UseEffectExample />}></Route>
            <Route path="/useRef"     element={<UseRefExample />}></Route>
            <Route path="/useContext" element={<UseContexExample />}></Route>
        </Routes>
    </div>
  );
}

export default App;
