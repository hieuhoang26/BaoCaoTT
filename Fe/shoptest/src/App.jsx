import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import useRouteElements from "./useRouteElements";

function App() {
  const routeElements = useRouteElements();
  return <>{routeElements}</>;
}

export default App;
