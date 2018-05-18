import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import app from './app';
import integracao from './integracao';

const rootReducer = combineReducers({
  routing: routerReducer,
  app,
  integracao
});

export default rootReducer;
