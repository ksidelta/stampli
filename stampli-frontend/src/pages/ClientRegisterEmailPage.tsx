import React from 'react';
import styled from 'styled-components';
import Header from "./components/Header";
import Box from '@mui/material/Box';
import TextField from './components/TextField';
import {MobilePage} from "./base/MobilePage";
import Button from "./components/Button";
import ButtonText from "./components/ButtonText";
import {Checkbox, FormControlLabel} from "@mui/material";

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

export default function RegisterEmailPage() {
    return (
        <MobilePage>
            <Header title="Zarejestruj Się (Email)"/>
            <Inner>
            <Box
                component="form"
                autoComplete="on"
            >
                <TextField text="Adres Email" required={true}/>
                <TextField text="Hasło" required={true}/>
                <FormControlLabel control={<Checkbox />} label="Akceptuję REGULAMIN" />
                <FormControlLabel control={<Checkbox />} label="Akceptuję POLITYKĘ PRYWATNOŚCI" />
                <Button text="Zaloguj Się" link="/stamples" button=""/>
                <ButtonText text="Zaloguj Się Adresem Email" link="/login/email"/>
            </Box>
            </Inner>
        </MobilePage>
    )
}