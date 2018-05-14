import React, { Component } from 'react';
import { Button, Table } from 'react-bootstrap';
// import PropTypes from 'prop-types';

class Integracao extends Component {
  state = {
    feedBack: []
  }

  render() {
    return (
      <div className="integracao-container">
        <div className="integracao-acoes">
          <Button
            onClick={this.integrarPedidos}
          >
            Integrar pedidos do ST no Eleanor
          </Button>
          <Button
            onClick={this.atualizarPedidos}
          >
            Atualizar pedidos no ST
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
            {this.state.feedBack.map((f) => {
              console.log(f.status);
              return (
                <tr>
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
  }
}

export default Integracao;
