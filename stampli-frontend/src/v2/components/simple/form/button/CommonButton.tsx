import React, { useContext } from 'react';
import styled from 'styled-components';

const Inner = styled.div`
  margin-bottom: var(--gap);
  border: 0.15em solid var(--text-color);
  padding: var(--sidewise-gap);
  padding-top: var(--ceiling-gap);
  padding-bottom: var(--ceiling-gap);

  cursor: pointer;

  :hover {
    background-color: var(--common-color);
  }

  :active {
    background-color: var(--solid-color);
  }

  display: flex;
  flex-direction: row;

  & .text {
    flex-grow: 1;
    text-align: center;
  }
`;

export const CommonButton = ({ text, onClick }: { text?: string; onClick?: () => void }) => {
  return (
    <Inner onClick={() => onClick && onClick()}>
      <div className={'text'}>{text ?? ''}</div>
    </Inner>
  );
};
