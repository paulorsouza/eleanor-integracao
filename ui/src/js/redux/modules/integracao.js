import axios from '../../axios-conf';
import { loadStart, loadEnd } from './app';

const FEEDBACK = 'integracao/FEEDBACK';

const initialState = {
  feedback: []
};

const reducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case FEEDBACK: {
      return { ...state, feedback: action.data };
    }
    default: return state;
  }
};

export const integrarPedidos = () => {
  return (dispatch) => {
    const url = '/integracao/firebase/pedidos/integrar';
    dispatch({
      type: FEEDBACK,
      data: []
    });
    loadStart(dispatch);
    axios.get(url)
      .then((res) => {
        dispatch({
          type: FEEDBACK,
          data: res.data
        });
      })
      .finally(() => {
        loadEnd(dispatch);
      });
  };
};

export const atualizarPedidos = () => {
  return (dispatch) => {
    const url = '/integracao/oracle/pedidos/atualizar';
    dispatch({
      type: FEEDBACK,
      data: []
    });
    loadStart(dispatch);
    axios.get(url)
      .then((res) => {
        dispatch({
          type: FEEDBACK,
          data: res.data
        });
      })
      .finally(() => {
        loadEnd(dispatch);
      });
  };
};

export default reducer;
