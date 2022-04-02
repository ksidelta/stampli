import styled from "styled-components";
import React from "react";

const Button = styled.button`
  width: 100%;
  max-width: 20rem;
`

export default function ButtonText (params: {text: string, link: string, color: string}) {
    return (
        <Button className="mdc-button mdc-button--touch">
            <span className="mdc-button__ripple"></span>
            <span className="mdc-button__touch"></span>
            <span className="mdc-button__label">{params.text}</span>
        </Button>
    )
}