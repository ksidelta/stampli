import React from 'react';
import styled from 'styled-components';
import { MobilePage } from "./base/MobilePage";
import Header from "./components/Header";
import Box from '@mui/material/Box';
import TextField from './components/TextField';
import Button from "./components/Button";
import ButtonText from "./components/ButtonText";

const Inner = styled.div`
  height: 100vh;
  > form {
    height: calc(100% - 3.2rem);
    display: flex;
    flex-flow: column nowrap;
    justify-content: center;
    align-items: center;
    margin: unset;
  }
`

export default function LoginEmailPage() {
    return (
        <MobilePage>
            <Inner>
                <Header title="Zaloguj Się (Email)"/>
                <Box
                    component="form"
                    autoComplete="on"
                >
                    <TextField text="Adres Email" required={true}/>
                    <TextField text="Hasło" required={true}/>
                    <Button text="Zaloguj Się" link="/stamples" button=""/>
                    <ButtonText text="Zarejestruj Się Adresem Email" link="/register/email"/>
                </Box>
            </Inner>
        </MobilePage>
    )
}