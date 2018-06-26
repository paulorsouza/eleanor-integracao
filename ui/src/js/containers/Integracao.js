import React from 'react';
import { Button, Table } from 'react-bootstrap';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { integrarPedidos, atualizarPedidos, integrarMaquinas } from '../redux/modules/integracao';

const mapStateToProps = state => ({
  feedback: state.integracao.feedback
});

const mapDispathToProps = dispatch => ({
  integrarPedidos: bindActionCreators(integrarPedidos, dispatch),
  atualizarPedidos: bindActionCreators(atualizarPedidos, dispatch),
  integrarMaquinas: bindActionCreators(integrarMaquinas, dispatch),
});

const Integracao = (props) => {
  return (
    <div className="integracao-container">
      <div className="integracao-acoes">
        <Button
          bsClass="btn btn-info"
          onClick={props.integrarPedidos}
        >
          Integrar pedidos do ST no Eleanor
        </Button>
        <Button
          bsClass="btn btn-info"
          onClick={props.atualizarPedidos}
        >
          Atualizar pedidos do eleanor no ST
        </Button>
        <Button
          bsClass="btn btn-info"
          onClick={props.integrarMaquinas}
        >
          Integrar maquinas do ST no eleanor
        </Button>
      </div>
      <Table responsive bordered>
        <thead>
          <tr>
            <th>Id Firebase</th>
            <th>Id Oracle</th>
            <th>Mensagem</th>
          </tr>
        </thead>
        <tbody>
          {props.feedback.map((f) => {
            return (
              <tr className={f.status === 'ERROR' ? 'error-row' : 'success-row'}>
                <td>{f.idFirebase}</td>
                <td>{f.idOracle}</td>
                <td>{f.msg}</td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </div>
  );
};

Integracao.propTypes = {
  integrarPedidos: PropTypes.func.isRequired,
  atualizarPedidos: PropTypes.func.isRequired,
  integrarMaquinas: PropTypes.func.isRequired,
  feedback: PropTypes.array.isRequired
};

export default connect(mapStateToProps, mapDispathToProps)(Integracao);
