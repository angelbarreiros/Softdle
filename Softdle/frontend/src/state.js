import { writable } from 'svelte/store';
export const languages = writable([]);
export const count = writable(0);
export const logged=writable(false)
export function getJwtToken() {
    const cookies = document.cookie.split(';').map(cookie => cookie.trim());
    const jwtCookie = cookies.find(cookie => cookie.startsWith('jwt='));
    return jwtCookie ? jwtCookie.split('=')[1] : null;
}
if (getJwtToken()!==null){
    logged.set(true)
}

