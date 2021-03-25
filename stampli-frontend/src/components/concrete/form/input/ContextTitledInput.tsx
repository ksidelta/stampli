import React from 'react';
import { FormOnChangeContext, FormValuesContext } from '../ContextForm';
import styled from 'styled-components';

export const ContextTitledInput = ({ title, name }: { title: string; name: string }) => (
  <FormValuesContext.Consumer>
    {values => (
      <FormOnChangeContext.Consumer>
        {onChange => (
          <Inner>
            <label>{title}</label>
            <input type={'text'} name={name} value={values[name]} onChange={onChange} />
          </Inner>
        )}
      </FormOnChangeContext.Consumer>
    )}
  </FormValuesContext.Consumer>
);

const Inner = styled.div`
  margin-bottom: calc(var(--gap));

  & label {
    display: block;
    text-align: left;

    font-size: calc(var(--gap));
    padding-bottom: calc(0.2 * var(--gap));
  }

  & input {
    display: block;
    width: 100%;

    padding-top: calc(var(--gap) * 0.5);
    padding-bottom: calc(var(--gap) * 0.5);
    padding-left: calc(var(--gap));

    border: 0.15rem black solid;
    border-radius: 0.2rem;

    font-size: calc(var(--gap));
    box-sizing: border-box;
  }
`;
