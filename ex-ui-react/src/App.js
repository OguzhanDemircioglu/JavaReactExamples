import './App.css';
import UseEffectExample from "./components/horizontalMenu/UseEffectExample";
import { Routes, Route } from "react-router-dom";
import HorizontalMenu from "./components/HorizontalMenu";
import Home from "./components/Home";
import UseRefExample from "./components/horizontalMenu/UseRefExample";
import UseContextExample from "./components/horizontalMenu/UseContextExample";
import UseStateExample from "./components/horizontalMenu/UseStateExample";
import UseMemoExample from "./components/horizontalMenu/UseMemoExample";
import UseReducerExample from "./components/horizontalMenu/UseReducerExample";
import UseCallBackExample from "./components/horizontalMenu/UseCallBackExample";
import VerticalMenu from "./components/VerticalMenu";
import DataTableEx from "./components/verticalMenu/DataTableEx";
import Slides from "./components/verticalMenu/Slides";
import ExcelOperations from "./components/verticalMenu/ExcelOperations";

function App() {
  return (
    <div className="App">
        <HorizontalMenu/>
        <VerticalMenu/>
        <Routes>
            {/*HorizontalMenu*/}
            <Route path="/"             element={<Home />}></Route>
            <Route path="/useEffect"    element={<UseEffectExample />}></Route>
            <Route path="/useRef"       element={<UseRefExample />}></Route>
            <Route path="/useContext"   element={<UseContextExample />}></Route>
            <Route path="/useState"     element={<UseStateExample />}></Route>
            <Route path="/useMemo"      element={<UseMemoExample />}></Route>
            <Route path="/useReducer"   element={<UseReducerExample />}></Route>
            <Route path="/useCallBack"  element={<UseCallBackExample />}></Route>
            {/*VerticalMenu*/}
            <Route path="/dataTableEx"  element={<DataTableEx />}></Route>
            <Route path="/slides"  element={<Slides />}></Route>
            <Route path="/excelOperations"  element={<ExcelOperations />}></Route>
        </Routes>
    </div>
  );
}

export default App;
