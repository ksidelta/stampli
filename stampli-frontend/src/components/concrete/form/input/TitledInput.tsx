import React from 'react';
import styled from 'styled-components';

export const TitledInput = ({
  title,
  type,
  value,
  onChange
}: {
  title: string;
  type: 'password' | 'text';
  value: string;
  onChange: (value: string) => void;
}) => (
  <Inner>
    <label>{title}</label>
    <input type={type} value={value} onChange={e => onChange(e.target.value)} />
  </Inner>
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
