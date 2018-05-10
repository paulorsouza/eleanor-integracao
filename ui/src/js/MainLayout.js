import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Agendamento from './containers/Agendamento';

const mainLayout = () => {
  return (
    <div>
      <Switch>
        <Route path="/" component={Agendamento} />
      </Switch>
    </div>
  );
};

export default mainLayout;
