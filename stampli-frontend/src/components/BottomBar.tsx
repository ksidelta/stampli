import React from 'react';
import styled from 'styled-components';
import {AppBar, Fab, IconButton, Toolbar} from "@mui/material";
import "../img/restaurant_menu_black_24dp.svg"
import "../img/approval_black_24dp.svg"
import "../img/qr_code_black_24dp.svg"
import "../img/discount_black_24dp.svg"
import "../img/menu_black_24dp.svg"
import Box from "@mui/material/Box";


const StyledFab = styled(Fab)({
    position: 'absolute',
    zIndex: 1,
    top: -30,
    left: 0,
    right: 0,
    margin: '0 auto',
});

// const StyledAppBar = styled(AppBar)(({ theme }) => ({
//     "--cradleMargin": "8px", // 1
//     "--fabRadius": "28px", // 2
//     "--cradleRadius": "calc(var(--fabRadius) + var(--cradleMargin))",
//     background: `
//     radial-gradient(
//       circle at 50% 0,
//       rgba(0, 0, 0, 0) var(--cradleRadius),
//       ${theme.palette.primary.main} var(--cradleRadius),
//       ${theme.palette.primary.main} 100%
//     )
//   `,
//     boxShadow: "none", // 3
//     filter: `drop-shadow(0 -1px 1px rgba(0, 0, 0, .2))` // 3
// }));

export default function BottomBar() {
    return (
        <>
            <AppBar position="fixed" color="primary" sx={{ top: 'auto', bottom: 0 }}>
                <Toolbar>
                    <IconButton color="inherit" aria-label="restaurant menu">
                        <img src={"/assets/img/restaurant_menu_black_24dp.svg"} alt={"Restaurant menu icon"} />
                        <p>menu</p>
                    </IconButton>
                    <IconButton color="inherit" aria-label="discounts">
                        <img src={"/assets/img/discount_black_24dp.svg"} alt={"Discount icon"} />
                        <p>discount</p>
                    </IconButton>
                    <StyledFab color="primary" aria-label="scan qr code">
                        <img src={"/assets/img/qr_code_black_24dp.svg"} alt={"QR Code icon"} />
                    </StyledFab>
                    <Box sx={{ flexGrow: 1 }} />
                    <IconButton color="inherit" aria-label="stamp">
                        <img src={"/assets/img/approval_black_24dp.svg"} alt={"Stamp icon"}/>
                        <p>stamples</p>
                    </IconButton>
                    <IconButton color="inherit" aria-label="options">
                        <img src={"/assets/img/menu_black_24dp.svg"} alt={"Options icon"}/>
                        <p>options</p>
                    </IconButton>
                </Toolbar>
            </AppBar>
        </>
    )
}