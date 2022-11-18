<template>
    <tr>
        <td>
        <div class="abc-checkbox">
            <input type="checkbox"
            id="checkbox15" :checked="checkboxes2[5]"
            @change="event => changeCheck(event, 'checkboxes2', 5)"
            />
            <label for="checkbox15" />
        </div>
        </td>
        <td>Version Control</td>
        <td class="text-right">$72 854.5</td>
        <td class="text-center">
        <b-badge variant="gray">Away</b-badge>
        </td>
    </tr>
</template>

<script>
import {mapActions} from "vuex";

export default {
    name: "BookmarkItem",
    props:{
        todo: Object,
    },
    methods: {
        
    },
}
</script>

<style>

</style>