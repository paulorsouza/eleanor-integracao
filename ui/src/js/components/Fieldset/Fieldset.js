import React from 'react';
import PropTypes from 'prop-types';

const Fieldset = (props) => {
  return (
    <fieldset className="groupbox-border">
      <legend className="groupbox-border">{props.title}</legend>
      <div className="control-group">
        {props.children}
      </div>
    </fieldset>
  );
};

Fieldset.propTypes = {
  title: PropTypes.string,
  children: PropTypes.element.isRequired
};
Fieldset.defaultProps = {
  title: ''
};

export default Fieldset;
