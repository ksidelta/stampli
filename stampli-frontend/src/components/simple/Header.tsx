import React from 'react';
import styled from 'styled-components';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const InnerHeader = styled.div`
  height: 5rem;
  background-color: var(--outstanding-color);

  display: flex;
  align-items: center;
  flex-direction: row;

  & .manipulator {
    height: 5rem;
    width: 5rem;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
  }

  & .manipulator div {
    font-size: 1rem;
  }

  & .text {
    font-size: 1.2rem;
    font-weight: 700;
  }
`;

export const Header = ({ title, icon }: { title: string; icon?: IconDefinition }) => (
  <InnerHeader>
    <div className="manipulator">
      <div>{icon && <FontAwesomeIcon className={'icon'} icon={icon} />}</div>
    </div>
    <div className="text">{title}</div>
  </InnerHeader>
);
