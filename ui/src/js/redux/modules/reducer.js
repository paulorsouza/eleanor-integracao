import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import app from './app';

const rootReducer = combineReducers({
  routing: routerReducer,
  app
});

export default rootReducer;
