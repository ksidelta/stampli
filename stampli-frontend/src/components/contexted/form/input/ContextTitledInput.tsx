import React from 'react';
import { FormOnChangeContext, FormSetValueContext, FormValuesContext } from '../ContextForm';
import styled from 'styled-components';
import { TitledInputComponent } from '../../../simple/form/input/TitledInputComponent';

export const ContextTitledInputComponent = ({
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
          <TitledInputComponent
            title={title}
            type={type}
            value={values[name]}
            onChange={value => onChange(name, value)}
          />
        )}
      </FormSetValueContext.Consumer>
    )}
  </FormValuesContext.Consumer>
);
