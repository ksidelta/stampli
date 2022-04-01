import React from 'react';
import styled from 'styled-components';
import { MobilePage } from './base/MobilePage';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons/faArrowLeft';
import { Content } from '../components/simple/container/layout/structure/Content';
import { BusinessLogin } from '../v2/components/complex/login/BusinessLogin';
import { InjectionContext } from '../components/stated/context/InjectionContext';
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
            <Button text="Zaloguj przy użyciu Apple" link="x" bg="" color=""/>
            <Button text="Zaloguj przy użyciu Google" link="x" bg="" color=""/>
            <Button text="Zaloguj przy użyciu Facebook" link="x" bg="" color=""/>
            <ButtonText text="Zaloguj Adresem Email" link="" color=""/>
            <ButtonText text="Zarejestruj Adresem Email" link="" color=""/>
        </section>
    </Inner>
  </MobilePage>
);
