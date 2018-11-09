/* generated @ 2018-11-09T15:28:41.158 */
declare namespace WearTypes {
  interface BoxInsetLayoutAttributes extends MainTypes.ViewGroupAttributes {
  }

  interface BoxInsetLayoutLayoutParamsAttributes extends MainTypes.ViewGroupLayoutParamsAttributes {
    boxedEdges?: string;
  }

  interface CircularProgressLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface SwipeDismissFrameLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface WearableActionDrawerViewAttributes extends WearableDrawerViewAttributes {
    drawerTitle?: string;
  }

  interface WearableDrawerLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface WearableDrawerViewAttributes extends MainTypes.FrameLayoutAttributes {
    android_elevation?: string;
    enableAutoPeek?: boolean;
  }

  interface WearableNavigationDrawerViewAttributes extends WearableDrawerViewAttributes {
  }

  interface WearableRecyclerViewAttributes extends RecyclerviewTypes.RecyclerViewAttributes {
    bezelWidth?: number;
    circularScrollingGestureEnabled?: boolean;
    scrollDegreesPerScreen?: number;
  }
}
