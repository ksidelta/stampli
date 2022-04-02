import React from 'react';
import styled from 'styled-components';

const Button = styled.button`
  width: 100%;
  max-width: 20rem;
`

export default function ButtonComponent (params: {text: string, link: string, button: string}) {
    const ButtonBase = () => (
        <>
            <span className="mdc-button__ripple"></span>
            <span className="mdc-button__touch"></span>
            <span className="mdc-button__label">{params.text}</span>
        </>
    )
    switch (params.button) {
        case "apple":
            return (
                <Button className="mdc-button mdc-button--raised mdc-button--touch apple-button">
                    <ButtonBase/>
                </Button>
            )
        case "google":
            return (
                <Button className="mdc-button mdc-button--raised mdc-button--touch google-button">
                    <ButtonBase/>
                </Button>
            )
        case "fb":
            return (
                <Button className="mdc-button mdc-button--raised mdc-button--touch fb-button">
                    <ButtonBase/>
                </Button>
            )
    }
    return (
        <Button className="mdc-button mdc-button--raised mdc-button--touch">
            <span className="mdc-button__ripple"></span>
            <span className="mdc-button__touch"></span>
            <span className="mdc-button__label">{params.text}</span>
        </Button>
    )
}