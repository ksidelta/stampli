import React from 'react';
import { FormOnChangeContext, FormSetValueContext, FormValuesContext } from '../ContextForm';
import styled from 'styled-components';
import { TitledInput } from '../../../concrete/form/input/TitledInput';

export const ContextTitledInput = ({
  title,
  name,
  type = 'text'
}: {
  title: string;
  name: string;
  type?: 'text' | 'password';
}) => (
  <FormValuesContext.Consumer>
    {values => (
      <FormSetValueContext.Consumer>
        {onChange => (
          <TitledInput title={title} type={type} value={values[name]} onChange={value => onChange(name, value)} />
        )}
      </FormSetValueContext.Consumer>
    )}
  </FormValuesContext.Consumer>
);
