import React from 'react';
import ReactDOM from 'react-dom';

import './css/main.scss';
import './css/century-gothic.css';
import { AppRouter } from './router/AppRouter';
import Logger from 'js-logger';

import 'regenerator-runtime';

Logger.useDefaults();

ReactDOM.render(<AppRouter />, document.getElementById('app'));
