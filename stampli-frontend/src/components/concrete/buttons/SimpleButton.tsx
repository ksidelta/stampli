import React, { useContext } from 'react';
import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { RouterServiceContext } from '../../../router/AppRouter';
import { RoutingService } from '../../../router/services/RoutingService';

const Inner = styled.div`
  margin-bottom: var(--gap);
  border: 0.15em solid var(--text-color);
  border-radius: var(--gap);
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
  }
  & .icon {
  }
`;

export const SimpleButton = ({
  text,
  onClick,
  icon
}: {
  text?: string;
  onClick?: (history: RoutingService) => void;
  icon?: IconDefinition;
}) => {
  const historyInstance = useContext(RouterServiceContext);

  return (
    <Inner onClick={() => onClick && onClick(historyInstance)}>
      <div className={'text'}>{text ?? ''}</div>
      {icon && <FontAwesomeIcon className={'icon'} icon={icon} />}
    </Inner>
  );
};
