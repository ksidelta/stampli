import styled from "styled-components";
import React from "react";

const Inner = styled.p`
  margin: 1rem;
  width: 20rem;
  border-radius: .6rem;
  cursor: pointer;
  display: flex;
  justify-content: center;
  text-transform: uppercase;
`

export default function ButtonText (params: {text: string, link: string, color: string}) {
    return (
        <Inner>{params.text}</Inner>
    )
}