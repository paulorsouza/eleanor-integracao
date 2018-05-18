import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Integracao from './containers/Integracao';
import Layout from './components/Layout';

const mainLayout = () => {
  return (
    <div>
      <Switch>
        <Layout>
          <Route path="/" component={Integracao} />
        </Layout>
      </Switch>
    </div>
  );
};

export default mainLayout;
