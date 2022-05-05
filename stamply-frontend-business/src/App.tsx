import React, {Fragment} from "react";
import {Route, Link, Routes, Navigate} from "react-router-dom";
import logo from './logo.svg';
import './App.css';
import Menu from './pages/Menu';
import BottomBar from "./components/BottomBar";
import QR from "./pages/QR";
import styled from "styled-components";

const Inner = styled.div`
  min-height: 100vh;
  display: grid;
  grid-template-rows: 1fr auto;
`

export default function App() {
    return (
        <Inner>
            <Routes>
                <Route path="/" element={<Navigate to="/menu"/>}/>
                <Route path="/menu" element={<Menu/>}/>
                <Route path="/qr" element={<QR/>}/>
                <Route path="/discounts" element={<img src={logo}/>}/>
                <Route path="/options" element={<img src={logo}/>}/>
            </Routes>
            <BottomBar/>
        </Inner>
    );
}