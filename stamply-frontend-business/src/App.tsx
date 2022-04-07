import React, { Fragment } from "react";
import { Route, Link, Routes} from "react-router-dom";
import logo from './logo.svg';
import './App.css';
import Menu from './pages/Menu';
import BottomBar from "./components/BottomBar";



export default function App() {
    return (
        <>
        <main>
            <nav>
                <ul>
                    <li><Link to="/">Home</Link></li>
                    <li><Link to="/about/E">About</Link></li>
                    <li><Link to="/contact">Contact</Link></li>
                </ul>
            </nav>
        </main>
    <Routes>
        <Route path="/" element={<Menu/>} />
        <Route path="/menu" element={() => <h1>Welcome!</h1>} />
        <Route path="/contact" element={<img src={logo}/>} />
    </Routes>
            <BottomBar/>
    </>
    );
}