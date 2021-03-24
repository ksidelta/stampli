import React from "react";
import styled from "styled-components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { IconDefinition } from "@fortawesome/fontawesome-svg-core";

const Inner = styled.div`
  margin: 1em;
  border: 0.15em solid var(--text-color);
  border-radius: 1em;
  padding: 2em;
  padding-top: 1.5em;
  padding-bottom: 1.5em;

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
  icon,
}: {
  text?: string;
  onClick?: () => void;
  icon?: IconDefinition;
}) => (
  <Inner onClick={() => onClick && onClick()}>
    <div className={"text"}>{text ?? ""}</div>
    {icon && <FontAwesomeIcon className={"icon"} icon={icon} />}
  </Inner>
);
