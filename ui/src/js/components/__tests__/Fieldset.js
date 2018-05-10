import React from 'react';

import { configure, shallow } from 'enzyme';
import Adapter from 'enzyme-adapter-react-15';
import Fieldset from '../Fieldset/Fieldset';

configure({
  adapter: new Adapter()
});

describe('Test <Fieldset /> component', () => {
  let wrapperComponent;
  let wrapperComponentDefault;

  beforeEach(() => {
    wrapperComponent = shallow(<Fieldset title="Test Component"><span>child</span></Fieldset>);
    wrapperComponentDefault = shallow(<Fieldset />);
  });
  it('Should have a Title prop with Test Component value.', () => {
    expect(wrapperComponent.instance().props.title).toEqual('Test Component');
    expect(wrapperComponent.find('span')).toHaveLength(1);
    expect(wrapperComponent.find('span')).not.toHaveLength(2);
  });
  it('Should not render <Fieldset /> component.', () => {
    expect(wrapperComponentDefault.instance().props.title).toEqual('');
    expect(wrapperComponentDefault.instance().props.child).toBeUndefined();
  });
});
