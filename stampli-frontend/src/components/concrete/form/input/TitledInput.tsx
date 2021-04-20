import React from 'react';
import styled from 'styled-components';
import { BasicLabel } from '../../label/BasicLabel';

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
    <BasicLabel>{title}</BasicLabel>
    <input type={type} value={value} onChange={e => onChange(e.target.value)} />
  </Inner>
);

const Inner = styled.div`
  margin-bottom: calc(var(--gap));

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
