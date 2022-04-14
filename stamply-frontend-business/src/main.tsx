import React from 'react'
import ReactDOM from 'react-dom'
import './main.css'
import App from './App'
import {BrowserRouter} from "react-router-dom";

ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
        <App />
      </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
)
