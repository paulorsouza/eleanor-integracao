import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import LoadingSpinner from './LoadingSpinner';

const mapStateToProps = state => ({
  isLoading: state.app.isLoading
});

const Layout = (props) => {
  return (
    <div>
      <LoadingSpinner isLoading={props.isLoading}>
        <main>
          {props.children}
        </main>
      </LoadingSpinner>
      <footer id="footer" >
        <b>Copyright © 2018 <a href="https://github.com/paulorsouza">prs</a></b>.
      </footer>
    </div>
  );
};


Layout.propTypes = {
  children: PropTypes.any.isRequired,
  isLoading: PropTypes.bool,
};

Layout.defaultProps = {
  isLoading: false,
};

export default connect(mapStateToProps, null)(Layout);
