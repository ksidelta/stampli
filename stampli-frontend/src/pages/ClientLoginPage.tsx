import React from 'react';
import styled from 'styled-components';
import { MobilePage } from './base/MobilePage';
import Header from "./components/Header";
import Button from "./components/Button";
import ButtonText from "./components/ButtonText";

const Inner = styled.div`
  height: 100vh;
  > section {
    height: 100%;
    display: flex;
    flex-flow: column nowrap;
    justify-content: center;
    align-items: center;
  }
`

export const ClientLoginPage = () => (
  <MobilePage>
    <Inner>
        <Header title="Zaloguj Się"/>
        <section>
            <Button text="Zaloguj przy użyciu Apple" link="x" button="apple"/>
            <Button text="Zaloguj przy użyciu Google" link="x" button="google"/>
            <Button text="Zaloguj przy użyciu Facebook" link="x" button="fb"/>
            <p>Albo</p>
            <ButtonText text="Zaloguj Adresem Email" link="" color=""/>
            <ButtonText text="Zarejestruj Adresem Email" link="" color=""/>
        </section>
    </Inner>
  </MobilePage>
);
